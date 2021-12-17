import { useState } from "react";

import { Link } from "react-router-dom";

import { AccountPageForm } from "../Components";

const AccountPage = () => {
  const [formData, setFormData] = useState({
    userId: "",
    password: "",
    repeatPassword: "",
    fName: "",
    lName: "",
    email: "",
    phone: "",
    addr1: "",
    addr2: "",
    city: "",
    state: "",
    zip: "",
    country: "",
    languagePreference: "",
    favcategory: "",
    bannername: "",
    listOption: false,
    bannerOption: false,
  });

  const handleInputField = (name) => (e) => {
    e.preventDefault();

    setFormData({
      ...formData,
      [name]: e.target.value,
    });
  };

  return (
    <>
      <AccountPageForm
        setFormData={setFormData}
        formData={formData}
        handleInputField={handleInputField}
        isReadOnly={false}
      />

      <Link to="/order/all" className="text-dark">
        My Orders
      </Link>
    </>
  );
};

export default AccountPage;
