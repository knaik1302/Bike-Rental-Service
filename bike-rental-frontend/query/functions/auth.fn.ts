import apiClient from "@/configs/axios.config"
import { TLogin, TSignup } from "@/types/auth.types"
import { TResponse } from "@/types/common.types"

export const loginFunction = async (payload: TLogin) => {
  const res = await apiClient.post<TResponse<{ Token: string }>>(
    "api/v1/auth/login",
    payload
  )
  return res.data
}

export const signUpFunction = async (payload: TSignup) => {
  const res = await apiClient.post<TResponse<{ email: string; name: string }>>(
    "api/v1/auth/signup",
    payload
  )
  return res.data
}
