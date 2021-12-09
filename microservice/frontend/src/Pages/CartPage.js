import { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import { Table, Col, Button } from "react-bootstrap";

import { removeItemFromCart } from "../Store/Actions/cart.actions";

import { CartTableRow } from "../Components";

const CartPage = () => {
  const dispatch = useDispatch();
  const { products, quantity, inventory } = useSelector((state) => state.cart);
  const items = useSelector((state) => state.cart.items);
  // const [refresh, setRefresh] = useState(false);
  //   const [totalAmount, setTotalAmount] = useState([]);

  const [cartTotal, setCartTotal] = useState(0);

  const calculateCartTotal = () => {
    //   let total = 0;
    //   for (var i = 0; i < cartItems.length; i++) {
    //     total =
    //       total +
    //       document.getElementById(cartItems[i].itemId).value *
    //         cartItems[i].listPrice;
    //   }
    // const totalArr = [];
    // cartItems.map((item, idx) => setTotalAmount([
    //         ...totalAmount,
    //         item.itemId: document.getElementById(item.itemId).value * item.listPrice
    //     ])
    // )
    //   setCartTotal(total);
  };

  const deleteItemFromCart = (itemId) => {
    dispatch(removeItemFromCart.request(itemId));
    // setRefresh(!refresh);
  };

  // useEffect(() => {
  //   const total = cartItems.map((item, idx) => item.quantity * item.listPrice);
  //   return () => {
  //     setCartTotal(total);
  //   };
  // }, [cartItems]);

  return (
    <div>
      <h2 className="text-center">Shopping Cart </h2>

      <Col md={8} className="mx-auto">
        <Table striped bordered hover size="sm">
          <thead>
            <tr>
              <th>Item ID</th>
              <th>Product ID</th>
              <th>Description</th>
              <th>In Stock?</th>
              <th>Quantity</th>
              <th>List Price (in $)</th>
              <th>Total Cost (in $)</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {/* {JSON.stringify(items)}
            {JSON.stringify(products)}
            {JSON.stringify(quantity)}
            {JSON.stringify(stock)} */}

            {Object.entries(items).map(([itemId, item], index) => {
              return (
                <CartTableRow
                  key={itemId}
                  item={item}
                  inventory={inventory}
                  quantity={quantity}
                  deleteItemFromCart={deleteItemFromCart}
                />
              );
            })}

            <tr>
              <td colSpan="2">Sub-Total: </td>
              <td>{cartTotal}</td>
              <td colSpan="5">
                <Button
                  className="btn btn-success"
                  size="sm"
                  onClick={calculateCartTotal}
                >
                  update cart
                </Button>
              </td>
            </tr>
          </tbody>
        </Table>
        <Button
          className="btn btn-success"
          size="sm"
          onClick={calculateCartTotal}
        >
          Proceed to Checkout
        </Button>
      </Col>
    </div>
  );
};

export default CartPage;
