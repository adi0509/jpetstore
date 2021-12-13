import { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getProductBySearchKeyword } from "../Store/Actions/search.actions";
import {
  Navbar,
  Nav,
  Container,
  Form,
  FormControl,
  Button,
  ListGroup,
  Row,
} from "react-bootstrap";

import { Link } from "react-router-dom";

const Header = () => {
  const [items, setItems] = useState([
    "fish",
    "dogs",
    "reptiles",
    "cats",
    "birds",
  ]);

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(document.getElementById("searchField").value);
    const keyword = document.getElementById("searchField").value;
    dispatch(getProductBySearchKeyword.request(keyword));
    navigate(`/search/${keyword}`);
  };

  return (
    <div>
      <Navbar bg="dark" expand="lg">
        <Container fluid>
          <Navbar.Brand>
            <Link to="/">JPetStore</Link>
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="navbarScroll" />
          <Navbar.Collapse id="navbarScroll">
            <Nav
              className="me-auto my-2 my-lg-0"
              style={{ maxHeight: "100px" }}
              navbarScroll
            >
              <Link to="/cart">Cart</Link>
              <Link to="/signin">Sign In</Link>
              <Link to="/register">My Account</Link>
              <Link to="/help">Help</Link>
              <Link to="/order/checkout">checkout</Link>
              <Link to="/order/confirmation">Confirmation</Link>
              <Link to="/order/summary">Summary</Link>
            </Nav>
            <Form className="d-flex" onSubmit={handleSubmit}>
              <FormControl
                type="search"
                placeholder="Search"
                className="me-2"
                aria-label="Search"
                id="searchField"
              />
              <Button variant="outline-success" type="submit">
                Search
              </Button>
            </Form>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <ListGroup
        className="col-md-4 mx-auto text-center border-none"
        horizontal
      >
        {items.map((item, idx) => {
          return (
            <ListGroup.Item className="border-none" key={idx}>
              <Link to={"/catalogue/" + item} className="text-dark p-0">
                {item}
              </Link>
            </ListGroup.Item>
          );
        })}
      </ListGroup>
    </div>
  );
};

export default Header;
