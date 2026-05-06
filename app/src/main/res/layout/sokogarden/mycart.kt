package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Data class for cart items
data class CartItem(val name: String, val price: Double, var quantity: Int)

// Adapter class for RecyclerView
class CartAdapter(
    private val items: List<CartItem>,
    private val onRemoveClick: (Int) -> Unit,
    private val onQuantityChange: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val view: android.view.View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.item_name)
        val priceText: TextView = view.findViewById(R.id.item_price)
        val quantityText: TextView = view.findViewById(R.id.quantity_text)
        val removeButton: Button = view.findViewById(R.id.remove_btn)
        val increaseButton: Button = view.findViewById(R.id.increase_btn)
        val decreaseButton: Button = view.findViewById(R.id.decrease_btn)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): CartViewHolder {
        val view = android.view.LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_cart_item_layout, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.nameText.text = item.name
        holder.priceText.text = "$%.2f".format(item.price)
        holder.quantityText.text = item.quantity.toString()

        holder.removeButton.setOnClickListener {
            onRemoveClick(position)
        }

        holder.increaseButton.setOnClickListener {
            item.quantity++
            notifyItemChanged(position)
            onQuantityChange()
        }

        holder.decreaseButton.setOnClickListener {
            if (item.quantity > 1) {
                item.quantity--
                notifyItemChanged(position)
                onQuantityChange()
            }
        }
    }

    override fun getItemCount(): Int = items.size
}

// Main Activity
class MyCartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var totalPriceText: TextView
    private lateinit var checkoutButton: Button

    // Declare adapter variable
    private lateinit var adapter: CartAdapter

    // List of cart items
    private val cartItems = mutableListOf<CartItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mycart)

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView)
        totalPriceText = findViewById(R.id.total_price)
        checkoutButton = findViewById(R.id.checkoutButton)

        // Add sample data
        cartItems.add(CartItem("Product 1", 10.0, 2))
        cartItems.add(CartItem("Product 2", 15.5, 1))
        cartItems.add(CartItem("Product 3", 7.25, 3))

        // Initialize adapter
        adapter = CartAdapter(
            cartItems,
            onRemoveClick = { position ->
                // Remove item from list
                cartItems.removeAt(position)
                // Notify adapter about removal
                adapter.notifyItemRemoved(position)
                // Update total price
                updateTotalPrice()
            },
            onQuantityChange = {
                updateTotalPrice()
            }
        )

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Display initial total price
        updateTotalPrice()

        // Set checkout button action
        checkoutButton.setOnClickListener {
            val intent = Intent(this, check_out::class.java)
            startActivity(intent)
        }
    }

    private fun updateTotalPrice() {
        val total = cartItems.sumOf { it.price * it.quantity }
        totalPriceText.text = "Total: \$%.2f".format(total)
    }
}