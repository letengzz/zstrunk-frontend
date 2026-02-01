import { defineStore } from 'pinia'
import { ref } from 'vue'

export interface Product {
  id: number
  name: string
  desc: string
  image: string
  images: string[]
  tag: string
  category: string
  subCategory: string
  specs: { label: string; value: string }[]
  parameters: { name: string; value: string }[]
  features: string[]
  specsText: string[]
  markdownPath: string
}

export const useProductsStore = defineStore(
  'productsStore',
  () => {
    const products = ref<Product[]>([])
    const isLoaded = ref(false)

    async function loadProducts() {
      if (isLoaded.value) {
        return products.value
      }

      try {
        const response = await fetch('/api/products')
        if (response.ok) {
          const result = await response.json()
          products.value = result.data || []
          isLoaded.value = true
        }
      } catch (error) {
        console.error('Failed to load products:', error)
      }

      return products.value
    }

    function setProducts(data: Product[]) {
      products.value = data
      isLoaded.value = true
    }

    function getProductById(id: number): Product | undefined {
      return products.value.find((p: Product) => p.id === id)
    }

    function getProductsByCategory(category: string): Product[] {
      return products.value.filter((p: Product) => p.category === category)
    }

    function getProductsBySubCategory(subCategory: string): Product[] {
      return products.value.filter((p: Product) => p.subCategory === subCategory)
    }

    function getHotProducts(): Product[] {
      return products.value.filter((p: Product) => p.tag === 'Hot')
    }

    function getNewProducts(limit: number): Product[] {
      return [...products.value]
        .sort((a: Product, b: Product) => (b.id || 0) - (a.id || 0))
        .slice(0, limit)
    }

    return {
      products,
      isLoaded,
      loadProducts,
      setProducts,
      getProductById,
      getProductsByCategory,
      getProductsBySubCategory,
      getHotProducts,
      getNewProducts,
    }
  },
)
