import Api from "./api";

const getProducts = async () => {
    return Api.get('/products').then((res) => {
        return res.data;
    });
}

export default getProducts;
