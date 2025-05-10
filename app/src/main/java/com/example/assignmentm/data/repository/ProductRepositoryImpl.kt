package com.example.assignmentm.data.repository
import com.example.assignmentm.data.remote.api.ProductApi
import com.example.assignmentm.data.remote.dto.ProductDto
import com.example.assignmentm.domain.model.Product
import com.example.assignmentm.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi
) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return try {
            api.getProducts().products.map { it.toDomain() }
        } catch (e: Exception) {
            mockProducts // Fallback to mock data on API failure
        }
    }

    override suspend fun getProductById(id: Int): Product {
        return try {
            api.getProductById(id).toDomain()
        } catch (e: Exception) {
            mockProducts.find { it.id == id }
                ?: throw IllegalArgumentException("Product not found")
        }
    }

    private fun ProductDto.toDomain(): Product {
        return Product(
            id = id,
            title = title,
            description = description,
            price = price,
            rating = rating,
            category = category,
            images = images
        )
    }

    private val mockProducts = listOf(
        Product(
            id = 1,
            title = "Essence Mascara Lash Princess",
            description = "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
            price = 9.99,
            rating = 4.94,
            category = "beauty",
            images = listOf(
                "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRvlewX3q8aHkMEcPoCsxJAkGNHynE1X0zSBPTAPL2s_fY6jNJp3Bisw6O2qZsmWP7YlqO4INGYcqZ8-YG-7iBatG-z8Ocd1vlKP7ItuUWolJ2fHKV52yQZUPc",
                "https://www.meesho.com/essence-lash-princess-curl-volume-mascara-pink/p/5chuv4",
                "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRvlewX3q8aHkMEcPoCsxJAkGNHynE1X0zSBPTAPL2s_fY6jNJp3Bisw6O2qZsmWP7YlqO4INGYcqZ8-YG-7iBatG-z8Ocd1vlKP7ItuUWolJ2fHKV52yQZUPc"
            )
        ),
        Product(
            id = 2,
            title = "Wireless Headphones",
            description = "High-quality wireless headphones with noise cancellation",
            price = 199.99,
            rating = 4.2,
            category = "Electronics",
            images = listOf(
                "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcTqwJpCGvLE3pYogKHFzes1KsjHuS3Xau4d9unSJlW-XxPoaff8czIpNN5zAC9F_FCYYb662GCMRbEqVu2U9SrjnmTBgqAdlV_Mb-mLxS1exLOInYyUN_t74Q",
                "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcSgLrl4Mj7FiMjzYRvvxB2o5pxfnEJrV1OSt6PbGAYJFbHFmaos-0KccqIUBXtkE_PWlXL8LocIteG8YjOTgAc7KhiY0h-l9LgvclXq9pjzrqIuUrYuvNGe6Xo",
                "https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcRgyR6GolZhdekEpQpEfYf97USuYgYGhA5lCwsJKZl19W75XMQdQbbR32DryH-nPMvlgOotghSzVT97NeP1ziE4QB7KdhvIYpj9_cEbSO_SqDThKHGXxWeSTQ"
            )
        ),
        Product(
            id = 3,
            title = "Smart Watch",
            description = "Fitness tracking smart watch with heart rate monitor",
            price = 249.99,
            rating = 4.0,
            category = "Wearables",
            images = listOf(
                "https://images.meesho.com/images/products/166723005/d2c4m_1200.jpg",
                "https://images.meesho.com/images/products/166723005/d2c4m_1201.jpg"
            )
        ),
        Product(
                id = 4,
        title = "Organic Face Cream",
        description = "Hydrating organic face cream with natural ingredients, suitable for all skin types. Provides long-lasting moisture and a radiant glow.",
        price = 29.99,
        rating = 4.7,
        category = "beauty",
        images = listOf(
            "https://www.bigbasket.com/media/uploads/p/l/40204533_1-himalaya-natural-glow-kesar-face-cream.jpg",
            "https://images.meesho.com/images/products/123456789/abc12_1200.jpg",
            "https://encrypted-tbn2.gstatic.com/shopping?q=1n9GcQbX7zY8vV3kW9mP5jL2xN4tR6sY7uW8vX9mQ2wP3kL4jN5tR6sY7uW8vX9mQ2wP3kL4jN5tR6sY7u"
        )
    ),
    Product(
    id = 5,
    title = "4K Smart TV",
    description = "55-inch 4K Ultra HD Smart TV with HDR, built-in streaming apps, and voice control.",
    price = 499.99,
    rating = 4.5,
    category = "Electronics",
    images = listOf(
    "https://encrypted-tbn1.gstatic.com/shopping?q=1n9GcR2wP3kL4jN5tR6sY7uW8vX9mQ2wP3kL4jN5tR6sY7uW8vX9mQ2wP3kL4jN5tR6sY7u",
    "https://www.bigbasket.com/media/uploads/p/l/12345678_1-samsung-55-inch-4k-smart-tv.jpg",
    "https://images.meesho.com/images/products/987654321/xyz12_1200.jpg"
    )
    ),
    Product(
    id = 6,
    title = "Running Shoes",
    description = "Lightweight running shoes with cushioned soles for maximum comfort and performance.",
    price = 89.99,
    rating = 4.3,
    category = "Clothing",
    images = listOf(
    "https://images.meesho.com/images/products/456789123/run12_1200.jpg",
    "https://encrypted-tbn0.gstatic.com/shopping?q=1n9GcS9mQ2wP3kL4jN5tR6sY7uW8vX9mQ2wP3kL4jN5tR6sY7u",
    "https://www.bigbasket.com/media/uploads/p/l/98765432_1-nike-running-shoes.jpg"
    )
    ),
    Product(
    id = 7,
    title = "Ceramic Non-Stick Pan",
    description = "12-inch ceramic non-stick frying pan, perfect for healthy cooking with easy cleanup.",
    price = 39.99,
    rating = 4.6,
    category = "Home",
    images = listOf(
    "https://encrypted-tbn2.gstatic.com/shopping?q=1n9GcT6sY7uW8vX9mQ2wP3kL4jN5tR6sY7uW8vX9mQ2wP3kL4jN5tR6sY7u",
    "https://www.bigbasket.com/media/uploads/p/l/45612378_1-ceramic-non-stick-pan.jpg",
    "https://images.meesho.com/images/products/123789456/pan12_1200.jpg"
    )
    )
    )
}