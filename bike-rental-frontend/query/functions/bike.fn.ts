import apiClient from "@/configs/axios.config"
import { GetAllRentalsResponse } from "@/types/bike.types"

export const getBikes = async () => {
  const res = await apiClient.get<GetAllRentalsResponse>("/api/v1/bikes")
  return res.data
}
