import { combineReducers } from "redux";

import auth from "./auth.reducer";
import cart from "./cart.reducer";
import catalogue from "./catalogue.reducer";
import search from "./search.reducer";

const allReducers = combineReducers({
  auth,
  cart,
  catalogue,
  search,
});

const rootReducers = (state, action) => {
  return allReducers(state, action);
};

export default rootReducers;
