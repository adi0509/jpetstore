import { useState } from "react";

import { CheckoutForm } from "../Components";

const CheckoutPage = () => {
  const [formData, setFormData] = useState({
    cardType: "test data",
    cardNumber: "test data",
    expiryDate: "test data",
    fName: "test data",
    lName: "test data",
    addr1: "test data",
    addr2: "test data",
    city: "test data",
    state: "test data",
    zip: "test data",
    country: "test data",
  });

  const handleInputField = (name) => (e) => {
    e.preventDefault();

    setFormData({
      ...formData,
      [name]: e.target.value,
    });
  };

  return (
    <div>
      <CheckoutForm formData={formData} handleInputField={handleInputField} />
    </div>
  );
};

export default CheckoutPage;
