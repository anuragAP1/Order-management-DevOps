import { useState, useEffect } from 'react'

const API_URL = 'http://localhost:8080/api/orders'

function App() {
  const [orders, setOrders] = useState([])
  const [itemName, setItemName] = useState('')
  const [quantity, setQuantity] = useState('')

  // Fetch orders once, when the component first loads
  useEffect(() => {
    fetchOrders()
  }, [])

  function fetchOrders() {
    fetch(API_URL)
      .then((response) => response.json())
      .then((data) => setOrders(data))
      .catch((error) => console.error('Error fetching orders:', error))
  }

const [errorMessage, setErrorMessage] = useState('')

function handleCreateOrder(event) {
  event.preventDefault()
  setErrorMessage('') // clear any previous error

  const newOrder = {
    itemName: itemName,
    quantity: parseInt(quantity),
    status: 'PENDING'
  }

  fetch(API_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(newOrder)
  })
    .then((response) => {
      if (!response.ok) {
        return response.json().then((errBody) => {
          throw new Error(errBody.error || 'Failed to create order')
        })
      }
      return response.json()
    })
    .then(() => {
      setItemName('')
      setQuantity('')
      fetchOrders()
    })
    .catch((error) => {
      setErrorMessage(error.message)
    })
}

  function handleDeleteOrder(id) {
    fetch(`${API_URL}/${id}`, { method: 'DELETE' })
      .then(() => fetchOrders()) // refresh the list after deleting
      .catch((error) => console.error('Error deleting order:', error))
  }

  return (
    <div style={{ maxWidth: '500px', margin: '40px auto', fontFamily: 'sans-serif' }}>
      <h1>Order Management</h1>

      <form onSubmit={handleCreateOrder} style={{ marginBottom: '20px' }}>
        <input
          type="text"
          placeholder="Item name"
          value={itemName}
          onChange={(e) => setItemName(e.target.value)}
          required
        />
        <input
          type="number"
          placeholder="Quantity"
          value={quantity}
          onChange={(e) => setQuantity(e.target.value)}
          required
        />
        <button type="submit">Add Order</button>
        {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
      </form>

      <ul>
        {orders.map((order) => (
          <li key={order.id}>
            #{order.id} — {order.itemName} (qty: {order.quantity}) — {order.status}
            <button onClick={() => handleDeleteOrder(order.id)} style={{ marginLeft: '10px' }}>
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default App