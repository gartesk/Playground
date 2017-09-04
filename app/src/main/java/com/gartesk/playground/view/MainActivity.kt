package com.gartesk.playground.view

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioGroup
import com.gartesk.playground.R
import com.gartesk.playground.data.RealmDataStorage
import com.gartesk.playground.data.entity.DataItem
import com.gartesk.playground.data.specification.RealmSpecification
import com.gartesk.playground.data.specification.RealmTrueSpecification
import com.gartesk.playground.data.specification.implementation.RealmItemCountBetweenSpecification
import com.gartesk.playground.data.specification.implementation.RealmItemNameSpecification

class MainActivity : AppCompatActivity() {

    private var realmDataStorage: RealmDataStorage? = null
    private var adapter: DataItemsAdapter? = null

    private val specifications: List<RealmSpecification<DataItem>> = listOf(RealmTrueSpecification(),
            RealmItemNameSpecification("First").and(RealmItemCountBetweenSpecification(5, 10)),
            RealmItemNameSpecification("Second").not().or(RealmItemCountBetweenSpecification(7, 15)),
            RealmItemNameSpecification("Third").or(RealmItemCountBetweenSpecification(7, 15).not()
                    .and(RealmItemNameSpecification("Fourth"))))
    private var currentSpecificationIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realmDataStorage = RealmDataStorage(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar) as Toolbar)
        findViewById(R.id.fab).setOnClickListener {
            showItemCreationDialog()
        }
        adapter = DataItemsAdapter()
        val recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        refreshAdapterData()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter?.dataItems = null
        realmDataStorage?.close()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            R.id.action_filter -> {
                showSpecificationFilterDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun refreshAdapterData() {
        adapter?.dataItems = realmDataStorage?.getItems(specifications[currentSpecificationIndex])
    }

    private fun showItemCreationDialog() {
        val dialogView = this.layoutInflater.inflate(R.layout.dialog_create, null)
        val nameEditText = dialogView.findViewById<TextInputEditText>(R.id.edit_text_name)
        val countEditText = dialogView.findViewById<TextInputEditText>(R.id.edit_text_count)
        val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle(R.string.title_add_item)
                .setPositiveButton(android.R.string.ok, { _, _ ->
                    val name = nameEditText.text.toString()
                    var count = 0
                    try {
                        count = Integer.parseInt(countEditText.text.toString())
                    } catch (numberFormatException: NumberFormatException) {
                    }
                    realmDataStorage?.saveItem(DataItem(name, count))
                    refreshAdapterData()
                })
                .create()
        dialog.setOnShowListener({
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val textWatcher = object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    button.isEnabled = !TextUtils.isEmpty(nameEditText.text) && !TextUtils.isEmpty(countEditText.text)
                }
            }
            nameEditText.addTextChangedListener(textWatcher)
            countEditText.addTextChangedListener(textWatcher)
            button.isEnabled = false
        })
        dialog.show()
    }

    private fun showSpecificationFilterDialog() {
        val dialogView = this.layoutInflater.inflate(R.layout.dialog_filter, null)
        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radio_group)
        val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle(R.string.title_filter)
                .setPositiveButton(android.R.string.ok, { _, _ ->
                    when (radioGroup.checkedRadioButtonId) {
                        R.id.radio_option_0 -> currentSpecificationIndex = 0
                        R.id.radio_option_1 -> currentSpecificationIndex = 1
                        R.id.radio_option_2 -> currentSpecificationIndex = 2
                        R.id.radio_option_3 -> currentSpecificationIndex = 3
                    }
                    refreshAdapterData()
                })
                .create()
        dialog.setOnShowListener({
            when (currentSpecificationIndex) {
                0 -> radioGroup.check(R.id.radio_option_0)
                1 -> radioGroup.check(R.id.radio_option_1)
                2 -> radioGroup.check(R.id.radio_option_2)
                3 -> radioGroup.check(R.id.radio_option_3)
            }
        })
        dialog.show()
    }
}
