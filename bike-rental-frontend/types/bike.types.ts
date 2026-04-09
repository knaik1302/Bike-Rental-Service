import { TArrayReponse, TResponse } from "./common.types"

export type TRentals = {
  id: number
  name: string
  category: "BIKE" | "SCOOTER"
  description: string | null
  hourlyRate: number
  status: boolean
}

export type GetAllRentalsResponse = TResponse<TArrayReponse<TRentals>>
