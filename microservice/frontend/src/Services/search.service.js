// import axios from "axios";
import { BASE_URL } from "../Constants/API";
import axiosInstance from "./interceptor";

export const SearchApi = {
  productsByKeyword: (keyword) => {
    console.log("keyword: " + keyword);
    return axiosInstance.post("/api/product/search", {
      keyword: keyword,
    });
  },
};
