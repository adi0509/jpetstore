// import axios from "axios";
import { BASE_URL } from "../Constants/API";
import axiosInstance from "./interceptor";

export const CatalogueApi = {
  productsByCategory: (id) => {
    console.log("category: " + id);
    return axiosInstance.get(`api/product/category/${id}`);
    // return axiosInstance.get("api/item/EST-1");
  },
  getItemByItemId: (id) => {
    return axiosInstance.get(`api/item/${id}`);
  },
  getItemsByProductId: (productId) => {
    console.log("productid: " + productId);
    return axiosInstance.get(`/api/item/product/${productId}`);
  },
  getProductByProductId: (productId) => {
    console.log("productid: " + productId);
    return axiosInstance.get(`/api/product/${productId}`);
  },
  getInventoryByItemId: (itemId) => {
    return axiosInstance.get(`/api/item/stock/${itemId}`);
  },
};
