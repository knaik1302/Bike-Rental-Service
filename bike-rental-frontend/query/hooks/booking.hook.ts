import { useMutation } from "@tanstack/react-query"
import { bookingFn } from "../functions/booking.fn"
import { toast } from "sonner"
import { AxiosError } from "axios"

export const useCreateBooking = () =>
  useMutation({
    mutationKey: ["booking"],
    mutationFn: bookingFn,
    onError: (e) => {
      if (e instanceof AxiosError) {
        toast.error(
          e.response?.data?.error?.message || "Something went wrong!!!"
        )
      } else toast.error("Something went wrong!!")
    },
    onSuccess:()=>{
      toast.success("Your Booking is completed successfully!!")
    }
  })
