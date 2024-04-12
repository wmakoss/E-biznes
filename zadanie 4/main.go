package main

import (
	"github.com/labstack/echo/v4"
	"math/rand"
	"net/http"
	"strconv"
	"time"
)

func generateID() int {
	for {
		id := rand.Intn(1000) // use 2000000000 for production
		if _, exists := products[id]; !exists {
			return id
		}
	}
}

type Product struct {
	ID    int     `json:"id"`
	Name  string  `json:"name"`
	Price float32 `json:"price"`
}

var products = map[int]*Product{}

func createProduct(c echo.Context) error {
	product := &Product{}
	if err := c.Bind(product); err != nil {
		return err
	}
	product.ID = generateID()
	products[product.ID] = product
	return c.JSON(http.StatusCreated, product)
}

func getProduct(c echo.Context) error {
	id := c.Param("id")
	productID, err := strconv.Atoi(id)
	if err != nil {
		return err
	}
	product, ok := products[productID]
	if !ok {
		return echo.NewHTTPError(http.StatusNotFound, "Product not found")
	}
	return c.JSON(http.StatusOK, product)
}

func getAllProducts(c echo.Context) error {
	return c.JSON(http.StatusOK, products)
}

func updateProduct(c echo.Context) error {
	id := c.Param("id")
	productID, err := strconv.Atoi(id)
	if err != nil {
		return err
	}
	product, ok := products[productID]
	if !ok {
		return echo.NewHTTPError(http.StatusNotFound, "Product not found")
	}
	updatedProduct := &Product{}
	if err := c.Bind(updatedProduct); err != nil {
		return err
	}
	product.Name = updatedProduct.Name
	product.Price = updatedProduct.Price
	return c.JSON(http.StatusOK, product)
}

func deleteProduct(c echo.Context) error {
	id := c.Param("id")
	productID, err := strconv.Atoi(id)
	if err != nil {
		return err
	}
	_, ok := products[productID]
	if !ok {
		return echo.NewHTTPError(http.StatusNotFound, "Product not found")
	}
	delete(products, productID)
	return c.NoContent(http.StatusNoContent)
}

func main() {
	rand.Seed(time.Now().UnixNano())
	e := echo.New()

	// Example endpoint
	//e.GET("/", func(c echo.Context) error {
	//	return c.String(http.StatusOK, "Hello, World!")
	//})

	e.POST("/products", createProduct)
	e.GET("/products/:id", getProduct)
	e.GET("/products", getAllProducts)
	e.PUT("/products/:id", updateProduct)
	e.DELETE("/products/:id", deleteProduct)

	e.Logger.Fatal(e.Start(":1323"))
}
