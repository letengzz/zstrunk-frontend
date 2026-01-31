export interface Product {
  id: number
  name: string
  desc: string
  image: string
  images: string[]
  tag: string
  category: string
  subCategory: string
  detailedDescription: string
  specs: { label: string; value: string }[]
  parameters: { name: string; value: string }[]
  features: string[]
  specsText: string[]
  markdownPath: string
}

const API_BASE = '/api/products'

let productsCache: Product[] | null = null

export async function getProducts(): Promise<Product[]> {
  if (productsCache) {
    return productsCache
  }

  try {
    const response = await fetch(`${API_BASE}`)
    if (!response.ok) {
      throw new Error('Failed to load products')
    }
    const result = await response.json()
    productsCache = result.data || []
    return productsCache || []
  } catch (error) {
    console.error('Error loading products:', error)
    return []
  }
}

export async function getProductsByCategoryFromApi(category: string): Promise<Product[]> {
  try {
    const response = await fetch(`${API_BASE}/category/${category}`)
    if (!response.ok) {
      throw new Error('Failed to load products by category')
    }
    const result = await response.json()
    return result.data || []
  } catch (error) {
    console.error('Error loading products by category:', error)
    return []
  }
}

export async function getCategoriesFromApi(): Promise<string[]> {
  try {
    const response = await fetch(`${API_BASE}/categories`)
    if (!response.ok) {
      throw new Error('Failed to load categories')
    }
    const result = await response.json()
    return result.data || []
  } catch (error) {
    console.error('Error loading categories:', error)
    return []
  }
}

export interface CategoryTreeNode {
  id: string
  label: string
  children?: CategoryTreeNode[]
  markdownPath?: string
}

export async function getCategoryTreeFromApi(): Promise<CategoryTreeNode[]> {
  try {
    const response = await fetch(`${API_BASE}/category-tree`)
    if (!response.ok) {
      throw new Error('Failed to load category tree')
    }
    const result = await response.json()
    return result.data || []
  } catch (error) {
    console.error('Error loading category tree:', error)
    return []
  }
}

export async function getProductsGroupedByCategoryFromApi(): Promise<Record<string, Product[]>> {
  try {
    const response = await fetch(`${API_BASE}/grouped`)
    if (!response.ok) {
      throw new Error('Failed to load grouped products')
    }
    const result = await response.json()
    return result.data || {}
  } catch (error) {
    console.error('Error loading grouped products:', error)
    return {}
  }
}

export async function getProductsByCategory(category: 'tanker' | 'excavator'): Promise<Product[]> {
  const products = await getProducts()
  return products.filter(product => product.category === category)
}

export async function getProductById(id: number): Promise<Product | undefined> {
  const products = await getProducts()
  return products.find(product => product.id === id)
}

export async function getProductByCategoryAndId(category: 'tanker' | 'excavator', id: number): Promise<Product | undefined> {
  const products = await getProductsByCategory(category)
  return products.find(product => product.id === id)
}

export async function getNewProducts(limit: number = 5): Promise<Product[]> {
  const products = await getProducts()
  console.log('All products:', products.length)
  console.log('Products with tag "new":', products.filter(p => p.tag === 'new'))
  return products
    .filter(product => product.tag === 'New')
    .slice(0, limit)
}

export interface CarouselItem {
  index: number
  mainTitle: string
  subTitle: string
}

let carouselCache: CarouselItem[] | null = null

export async function getCarouselConfig(): Promise<CarouselItem[]> {
  if (carouselCache) {
    return carouselCache
  }

  try {
    const response = await fetch('/api/config/carousel')
    if (!response.ok) {
      throw new Error('Failed to load carousel config')
    }
    const result = await response.json()
    carouselCache = result.data || []
    return carouselCache || []
  } catch (error) {
    console.error('Error loading carousel config:', error)
    return []
  }
}

export interface Solution {
  title: string
  desc: string
  image: string
}

let solutionsCache: Solution[] | null = null

export async function getSolutionsConfig(): Promise<Solution[]> {
  if (solutionsCache) {
    return solutionsCache
  }

  try {
    const response = await fetch('/api/config/solutions')
    if (!response.ok) {
      throw new Error('Failed to load solutions config')
    }
    const result = await response.json()
    solutionsCache = result.data || []
    return solutionsCache || []
  } catch (error) {
    console.error('Error loading solutions config:', error)
    return []
  }
}
