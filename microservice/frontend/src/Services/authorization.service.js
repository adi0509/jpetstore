import axiosInstance from "./interceptor";
// import { CLIENT_ID, CLIENT_SECRET, RESOURCE } from "../Constants/API";

export const AuthorizationApi = {
  guest: () => {
    const params = new URLSearchParams();
    // params.append("grant_type", "client_credentials");
    // params.append("client_id", CLIENT_ID);
    // params.append("client_secret", CLIENT_SECRET);
    // params.append("resource", RESOURCE);
    // return axiosInstance.post(
    //   "https://accounts.accesscontrol.windows.net/a733be43-c496-4145-9dce-25112486f234/tokens/OAuth/2?Content-Type=application/x-www-form-urlencoded",
    //   params,
    //   {
    //     headers: {
    //       "Content-Type": "application/x-www-form-urlencoded",
    //     },
    //   }
    // );
  },
  validateCredentials: (credentials) => {
    console.log("Credentials: " + credentials.username + credentials.password);
    const reqBody = {
      username: credentials.username,
      password: credentials.password,
    };
    return axiosInstance.post("api/signin", reqBody);
  },
  saveCredentials: (accountDetails) => {
    return axiosInstance.post("api/signup", accountDetails);
  },
};
