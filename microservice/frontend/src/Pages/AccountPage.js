import { useState } from "react";

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
    <AccountPageForm
      setFormData={setFormData}
      formData={formData}
      handleInputField={handleInputField}
    />
  );
};

export default AccountPage;
