import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router";
import { ItemCard } from "../Components";

import {
  getItemByItemId,
  getInventoryByItemId,
  getProductByProductId,
} from "../Store/Actions/catalogue.actions";

const ItemPage = () => {
  const { itemId, productId } = useParams();
  const dispatch = useDispatch();

  const { item, stock, product } = useSelector((state) => state.catalogue);

  useEffect(() => {
    dispatch(getItemByItemId.request(itemId));
    dispatch(getProductByProductId.request(productId));
    dispatch(getInventoryByItemId.request(itemId));
  }, []);

  return (
    <div className="col-md-12 row">
      <ItemCard product={product} stock={stock} item={item} />
    </div>
  );
};

export default ItemPage;
