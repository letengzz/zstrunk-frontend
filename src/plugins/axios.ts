// import axios from 'axios'

// const api = axios.create({
//   baseURL: '/api',
//   timeout: 30000,
//   headers: {
//     'Content-Type': 'application/json',
//   },
// })

// api.interceptors.request.use(
//   (config) => {
//     console.log('[Axios Request]', config.method?.toUpperCase(), config.url)
//     return config
//   },
//   (error) => {
//     console.error('[Axios Request Error]', error)
//     return Promise.reject(error)
//   },
// )

// api.interceptors.response.use(
//   (response) => {
//     console.log('[Axios Response]', response.config.url, response.status)
//     return response
//   },
//   (error) => {
//     console.error('[Axios Response Error]', error.config?.url, error.response?.status, error.message)
//     return Promise.reject(error)
//   },
// )

// export default api
