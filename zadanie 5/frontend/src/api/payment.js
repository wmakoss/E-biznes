import Api from "./api";

const sendPayment = async (data) => {
    return Api.post('/payment', data)
        .then((res) => {
            return res.data;
        });
}

export default sendPayment;
