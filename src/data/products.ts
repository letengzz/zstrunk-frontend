import productsData from './products.json'

export interface Product {
  id: number
  name: string
  desc: string
  image: string
  images?: string[]
  tag: string
  category: 'tanker' | 'excavator'
  subCategory?: string
  detailedDescription?: string
  specs?: { label: string; value: string }[]
  parameters?: { name: string; value: string }[]
  features?: string[]
  specsText?: string[]
  markdownPath?: string
}

export const products: Product[] = productsData as Product[]

export function getProductById(id: number): Product | undefined {
  return products.find(p => p.id === id)
}

export function getProductsByCategory(category: 'tanker' | 'excavator'): Product[] {
  return products.filter(p => p.category === category)
}
