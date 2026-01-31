export interface Product {
  id: number
  name: string
  desc: string
  image: string
  images: string[]
  tag: string
  category: 'tanker' | 'excavator'
  subCategory: string
  detailedDescription: string
  specs: { label: string; value: string }[]
  parameters: { name: string; value: string }[]
  features: string[]
  specsText: string[]
  markdownPath: string
}

let productsCache: Product[] | null = null

export async function getProducts(): Promise<Product[]> {
  if (productsCache) {
    return productsCache
  }

  try {
    const response = await fetch('/data/products.json')
    if (!response.ok) {
      throw new Error('Failed to load products')
    }
    productsCache = await response.json()
    return productsCache || []
  } catch (error) {
    console.error('Error loading products:', error)
    return []
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
