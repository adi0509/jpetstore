import { useEffect } from "react";

import { InputGroup, FormControl, Button } from "react-bootstrap";

const CartTableRow = (props) => {
  const { deleteItemFromCart, item, product, quantity, inventory } = props;

  return (
    <tr>
      <td>{item.itemId}</td>
      <td>{item.productId}</td>
      <td>
        {item.attr1} {item.attr2} {item.attr3} {item.attr4} {item.attr5}
      </td>
      <td>{inventory[item.itemId] > 0 ? "True" : "False"}</td>

      <td>
        <InputGroup size="sm" className="mb-3">
          <FormControl
            aria-label="Small"
            aria-describedby="inputGroup-sizing-sm"
            id={item.itemId}
            defaultValue={quantity[item.itemId]}
          />
        </InputGroup>
      </td>
      <td>{item.listPrice}</td>
      <td>{item.listPrice * quantity[item.itemId]}</td>
      <td>
        <Button
          className="btn btn-danger"
          size="sm"
          onClick={() => deleteItemFromCart(item.itemId)}
        >
          remove
        </Button>
      </td>
    </tr>
  );
};

export default CartTableRow;
