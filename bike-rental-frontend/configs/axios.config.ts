import { useCommonStore } from "@/store/common.store"
import axios from "axios"

const apiClient = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_BASE_URL || "http://localhost:8080",
})

apiClient.interceptors.request.use((config) => {
  const token = useCommonStore.getState().token

  const publicRoutes = [
    "/api/v1/bikes",
    "/api/v1/auth/login",
    "/api/v1/auth/register",
  ]

  const url = config.url ?? ""
  const isPublicRoute = publicRoutes.some((route) => url.includes(route))

  if (!isPublicRoute && token) {
    config.headers?.set("Authorization", `Bearer ${token}`)
  }

  return config
})

export default apiClient
