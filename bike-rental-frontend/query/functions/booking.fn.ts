import apiClient from "@/configs/axios.config"
import { TBook, TBookingResonse } from "@/types/booking.types"

export const bookingFn = async (payload: TBook) => {
  const res = await apiClient.post<TBookingResonse>("/api/v1/bookings", payload)
  return res.data
}
