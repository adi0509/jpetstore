import * as CartActions from "../Actions/cart.actions";

const initialState = {
  items: {
    "EST-1": {
      itemId: "EST-1",
      productId: "FI-SW-01",
      description: "Large angel fish",
      inStock: "true",
      quantity: 1,
      listPrice: 16.5,
      totalCost: 16.5,
    },
    "EST-2": {
      itemId: "EST-2",
      productId: "FI-SW-01",
      description: "Large angel fish",
      inStock: "true",
      quantity: 1,
      listPrice: 13.5,
      totalCost: 13.5,
    },
  },
  products: {
    "EST-1": {
      itemId: "EST-1",
      productId: "FI-SW-01",
      description: "Large angel fish",
      inStock: "true",
      quantity: 1,
      listPrice: 16.5,
      totalCost: 16.5,
    },
    "EST-2": {
      itemId: "EST-2",
      productId: "FI-SW-01",
      description: "Large angel fish",
      inStock: "true",
      quantity: 1,
      listPrice: 13.5,
      totalCost: 13.5,
    },
  },
  quantity: {
    "EST-1": 1,
    "EST-2": 1,
  },
  inventory: {
    "EST-1": 1000,
    "EST-2": 200,
  },
  cartTotal: 2,
  loadingInventory: false,
  loadingProduct: false,
  errorInventory: null,
  errorProduct: null,
};

export default function (state = initialState, action) {
  switch (action.type) {
    case CartActions.ADD_ITEM:
      return {
        ...state,
        items: {
          ...state.items,
          [action.payload.itemId]: action.payload,
        },
        quantity: {
          ...state.quantity,
          [action.payload.itemId]:
            state.quantity[action.payload.itemId] === undefined
              ? 1
              : state.quantity[action.payload.itemId] + 1,
        },
      };
    case CartActions.UPDATE_QUANTITY_OF_ITEM:
      return {
        ...state,
        quantity: {
          ...state.quantity,
          [action.payload.itemId]: action.payload.quantity,
        },
      };
    case CartActions.REMOVE_ITEM:
      const items = state.items;
      delete items[action.payload];
      return {
        ...state,
        items: items,
        quantity: {
          ...state.quantity,
          [action.payload]: undefined,
        },
        inventory: {
          ...state.inventory,
          [action.payload]: undefined,
        },
      };
    case CartActions.FETCH_INVENTORY_OF_ITEM_CART.REQUEST:
      return {
        ...state,
        loadingInventory: true,
      };
    case CartActions.FETCH_INVENTORY_OF_ITEM_CART.SUCCESS:
      console.log(action.payload.data);
      return {
        ...state,
        inventory: {
          ...state.inventory,
          [action.payload.data.itemId]: action.payload.data.quantity,
        },
        loadingInventory: false,
      };
    case CartActions.FETCH_INVENTORY_OF_ITEM_CART.FAILURE:
      return {
        ...state,
        loadingInventory: false,
        errorInventory: action.payload,
      };
    case CartActions.FETCH_PRODUCT_BY_PRODUCTID_CART.REQUEST:
      return {
        ...state,
        loadingProduct: true,
      };
    case CartActions.FETCH_PRODUCT_BY_PRODUCTID_CART.SUCCESS:
      return {
        ...state,
        products: {
          ...state.products,
          [action.payload.productId]: action.payload,
        },
        loadingProduct: false,
      };
    case CartActions.FETCH_PRODUCT_BY_PRODUCTID_CART.FAILURE:
      console.log(action);
      return {
        ...state,
        loadingProduct: false,
        errorProduct: action.payload,
      };
    default:
      return state;
  }
}
