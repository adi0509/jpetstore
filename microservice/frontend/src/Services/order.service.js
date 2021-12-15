// import axios from "axios";
import { ORDER_BASE_URL } from "../Constants/API";
import axiosInstance from "./interceptor";

export const OrderApi = {
  ordersByUserId: (userId) => {
    console.log("userid: " + userId);
    return axiosInstance.get(`/${ORDER_BASE_URL}/api/order/user/${userId}`);
  },
};
