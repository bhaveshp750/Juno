package com.bhaveshp750.junoassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhaveshp750.junoassignment.R
import com.bhaveshp750.junoassignment.modal.Transaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), YourHoldingsAdapter.OnButtonClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var progressView: ProgressBar
    private lateinit var errorTextView: TextView
    private lateinit var priceLayout: ConstraintLayout
    private lateinit var depositButton: Button
    private lateinit var title: TextView
    private lateinit var subtitle: TextView
    private lateinit var title2: TextView

    private lateinit var yourHoldingsRecyclerView: RecyclerView
    private lateinit var recentTransactionsRecyclerView: RecyclerView
    // 0: Empty State, 1: Value State
    private var state = 1
    private lateinit var valueStateButton: Button
    private lateinit var emptyStateButton: Button
    private var allTransactionsList: MutableList<Transaction> = mutableListOf()
    private val addTransactionList: MutableList<Transaction> = mutableListOf()
    private lateinit var recentTxnAdapter: RecentTransactionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        progressView = findViewById(R.id.progress_view)
        errorTextView = findViewById(R.id.error_text_view)

        priceLayout = findViewById(R.id.price_layout)
        depositButton = findViewById(R.id.deposit_button)
        title = findViewById(R.id.title)
        subtitle = findViewById(R.id.subtitle)
        title2 = findViewById(R.id.title2)

        yourHoldingsRecyclerView = findViewById(R.id.your_crypto_holdings_recycler_view)
        recentTransactionsRecyclerView = findViewById(R.id.recent_transactions_recycler_view)

        val yourHoldingsAdapter = YourHoldingsAdapter(arrayListOf(), state, this)
        yourHoldingsRecyclerView.layoutManager = LinearLayoutManager(this)
        yourHoldingsRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        yourHoldingsRecyclerView.adapter = yourHoldingsAdapter

        recentTxnAdapter = RecentTransactionsAdapter(arrayListOf())
        recentTransactionsRecyclerView.layoutManager = LinearLayoutManager(this)
        recentTransactionsRecyclerView.adapter = recentTxnAdapter

        viewModel.homeDto.observe(this) { homeDto ->
            homeDto?.let {
                Log.d(TAG, "onCreate: homeDto")
                yourHoldingsAdapter.updateList(it.yourCryptoHoldings, state)
                if(state == 1) {
                    priceLayout.visibility = View.VISIBLE
                    title.text = it.cryptoBalance.title
                    subtitle.text = it.cryptoBalance.subtitle
                    title2.text = "$"+it.cryptoBalance.currentBalanceInUSD
                    allTransactionsList.clear()
                    allTransactionsList = it.allTransactions as ArrayList<Transaction>
                }
                recentTxnAdapter.updateList(it.allTransactions)

            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            isLoading?.let {
                Log.d(TAG, "onCreate: isLoading $isLoading")
                progressView.visibility = if (it) View.VISIBLE else View.GONE
                valueStateButton.isClickable = !it
                emptyStateButton.isClickable = !it
            }
        }

        viewModel.fetchError.observe(this) { isError ->
            isError?.let {
                Log.d(TAG, "onCreate: fetchError $isError")
                errorTextView.text = "Something went wrong!\nAn Error has occurred!!"
                errorTextView.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        valueStateButton = findViewById(R.id.value_state_button)
        emptyStateButton = findViewById(R.id.empty_state_button)

        valueStateButton.setOnClickListener {
            if(state != 1){
                priceLayout.visibility = View.VISIBLE
                depositButton.visibility = View.GONE
                state = 1
                viewModel.getValueState()
                it.backgroundTintList = AppCompatResources.getColorStateList(this, R.color.purple_700)
                emptyStateButton.backgroundTintList = AppCompatResources.getColorStateList(this, R.color.purple_200)
            }
        }
        emptyStateButton.setOnClickListener {
            if(state != 0) {
                priceLayout.visibility = View.GONE
                depositButton.visibility = View.VISIBLE
                state = 0
                viewModel.getEmptyState()
                it.backgroundTintList = AppCompatResources.getColorStateList(this, R.color.purple_700)
                valueStateButton.backgroundTintList = AppCompatResources.getColorStateList(this, R.color.purple_200)
            }
        }
    }

    override fun onDepositClicked(cryptoName: String) {
        Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()
    }

    override fun onBuyClicked(cryptoName: String) {
        for (i in 0 until allTransactionsList.size) {
            val txn = allTransactionsList[i]
            if (txn.title.contains(cryptoName) ){
                addTransactionList.add(txn)
                recentTxnAdapter.addTxn(txn)
                Log.d(TAG, "onBuyClicked: true $txn")
                break
            }else{
                Log.d(TAG, "onBuyClicked: false $txn")

            }
        }
    }
}