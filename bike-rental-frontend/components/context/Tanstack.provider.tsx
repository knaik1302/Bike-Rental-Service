"use client "

import queryClient from "@/configs/query.config"
import { QueryClientProvider } from "@tanstack/react-query"

const TanstackProvider = ({ children }: { children: React.ReactNode }) => {
  return (
    <QueryClientProvider client={queryClient}>{children}</QueryClientProvider>
  )
}
export default TanstackProvider
