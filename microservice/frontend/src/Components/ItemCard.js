import { Card, Button } from "react-bootstrap";

const ItemCard = (props) => {
  const { item, product, stock } = props;
  return (
    <div>
      <Card style={{ width: "18rem" }} className="mx-auto mb-4 mt-4">
        <Card.Body>
          <Card.Text>{product.descn}</Card.Text>
          <Card.Title>{item.itemId}</Card.Title>
          <Card.Subtitle className="mb-2 text-muted">
            {item.attr1} {item.attr2} {item.attr3} {item.attr4} {item.attr5}
            {product.name}
          </Card.Subtitle>
          <Card.Text>{product.name}</Card.Text>
          <Card.Text>{stock > 0 ? "In Stock" : "Back Ordered"}</Card.Text>
          <Card.Text>${item.listPrice}</Card.Text>

          <Button className="btn btn-success">Add To Cart</Button>
        </Card.Body>
      </Card>
    </div>
  );
};

export default ItemCard;
