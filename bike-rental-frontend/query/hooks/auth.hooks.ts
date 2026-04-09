import { useMutation } from "@tanstack/react-query"
import { loginFunction, signUpFunction } from "../functions/auth.fn"
import { useRouter } from "next/navigation"
import { toast } from "sonner"
import { useToken } from "@/store/common.store"

export const useLogin = () => {
  const { push } = useRouter()
  const {setToken}=useToken()
  return useMutation({
    mutationKey: ["login"],
    mutationFn: loginFunction,
    onSuccess: ({data}) => {
      toast.success("Login Successful")
      setToken(data.Token)
      push("/rentals")
    },
    onError: () => {
      toast.error("Login Failed")
    },
  })
}

export const useSignUp = () =>
  useMutation({
    mutationKey: ["signup"],
    mutationFn: signUpFunction,
    onSuccess: () => {
      toast.success("Signup Successful,Please Login to proceed.")
    },
    onError: () => {
      toast.error("Signup Failed")
    },
  })
