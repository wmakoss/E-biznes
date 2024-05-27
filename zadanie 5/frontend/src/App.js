import './App.css';
import {Products} from "./components/products"
import {Payment} from "./components/payment"

function App() {
    return (
        <div className="App">
            <Products/>
            <Payment/>
            <div style={{clear: "both"}}></div>
        </div>
    );
}

export default App;
