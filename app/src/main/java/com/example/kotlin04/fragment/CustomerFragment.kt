package com.example.kotlin04.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin04.R
import com.example.kotlin04.adapter.CustomerAdapter
import com.example.kotlin04.model.Customer
import kotlinx.android.synthetic.main.fragment_customer.view.*


class CustomerFragment : Fragment() {
    private var listCustomer = ArrayList<Customer>()
    private val customerAdapter: CustomerAdapter = CustomerAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_customer, container, false)
        addDumpData()
        view.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            customerAdapter.setData(listCustomer)
            adapter = customerAdapter
        }
        return view
    }

    private fun addDumpData() {
        listCustomer.add(Customer(CustomerAdapter.VIEW_TYPE_ONE, "Alert", R.drawable.icons_8_alarm))
        listCustomer.add(
            Customer(
                CustomerAdapter.VIEW_TYPE_ONE,
                "Predictions",
                R.drawable.icons_8_left_and_right_arrows
            )
        )
        listCustomer.add(
            Customer(
                CustomerAdapter.VIEW_TYPE_ONE,
                "Saved elements",
                R.drawable.icons_8_pin
            )
        )
        listCustomer.add(
            Customer(
                CustomerAdapter.VIEW_TYPE_ONE,
                "Remove Ads",
                R.drawable.icons_8_no_entry
            )
        )
        listCustomer.add(
            Customer(
                CustomerAdapter.VIEW_TYPE_TWO,
                "Select Stocks",
                R.drawable.icons_8_profit_2
            )
        )
        listCustomer.add(
            Customer(
                CustomerAdapter.VIEW_TYPE_TWO,
                "Currency Exchange",
                R.drawable.icons_8_swap
            )
        )
        listCustomer.add(
            Customer(
                CustomerAdapter.VIEW_TYPE_TWO,
                "Webinar",
                R.drawable.icons_8_video_call
            )
        )
        listCustomer.add(
            Customer(
                CustomerAdapter.VIEW_TYPE_TWO,
                "Best Broker",
                R.drawable.icons_8_rent
            )
        )
        listCustomer.add(
            Customer(
                CustomerAdapter.VIEW_TYPE_THREE,
                "Select Stocks",
                R.drawable.icons_8_profit_2
            )
        )
    }

}