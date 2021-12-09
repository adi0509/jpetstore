import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import { Routes, Route, Link } from "react-router-dom";

//import custom pages
import {
  HomePage,
  NotFoundPage,
  CataloguePage,
  ProductPage,
  SignInPage,
  AccountPage,
  CartPage,
  SearchResultPage,
  ItemPage,
  CheckoutPage,
} from "./Pages/index";

//import custom components
import { Header, Footer } from "./Components/index";

const App = () => {
  return (
    <div className="container App">
      <Header />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/signin" element={<SignInPage />} />
        <Route path="/register" element={<AccountPage />} />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/checkout" element={<CheckoutPage />} />
        <Route path="/catalogue/:id" element={<CataloguePage />} />
        <Route path="/catalogue/:id/:productId" element={<ProductPage />} />
        <Route
          path="/catalogue/:id/:productId/:itemId"
          element={<ItemPage />}
        />
        <Route path="/search/:keywords" element={<SearchResultPage />} />
        {/* 404 page */}
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
      <Footer />
    </div>
  );
};

export default App;
