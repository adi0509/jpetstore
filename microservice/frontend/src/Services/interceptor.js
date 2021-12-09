import axios from "axios";
import { BASE_URL } from "../Constants/API";

const axiosInstance = axios.create({
  // baseURL: BASE_URL,
});

const requestHandler = (request) => {
  // const token = localStorage.getItem("token");
  // const lang = localStorage.getItem("i18nextLng");
  // if (lang) {
  //   request.headers["Content-Language"] = lang;
  // }
  // if (token) {
  //   request.headers.Authorization = `bearer ${token}`;
  // }

  // request.headers.common["Accept"] = "*/*";
  // request.headers.get["Accept"] = "*/*";
  // request.headers["get"].append("Access-Control-Allow-Origin", "*");
  // request.headers["get"].append("Access-Control-Allow-Credentials", "true");
  // request.header["Access-Control-Allow-Origin"] = "*";
  // request.header["Access-Control-Allow-Methods"] =
  //   "GET, POST, PATCH, PUT, DELETE, OPTIONS";
  // request.header["Access-Control-Allow-Headers"] =
  //   "Origin, Content-Type, X-Auth-Token";

  console.log(request.headers);
  return request;
};

const errorHandler = async (error) => {
  if (error.response.status === 401) {
    window.location.href = "/401";
  }
  if (error.response.status === 403) {
    window.location.href = "/403";
  }
  if (error.response.status === 404) {
    console.log(error);
    // window.location.href = "/404";
  }

  if (error.response.status >= 400) {
    await Promise.reject(error.response);
  }
};

const successHandler = (response) => {
  return response;
};

axiosInstance.interceptors.request.use((request) => requestHandler(request));
axiosInstance.interceptors.response.use(
  (response) => successHandler(response),
  (error) => errorHandler(error)
);

export default axiosInstance;
