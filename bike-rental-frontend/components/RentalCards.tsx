"use client"
import Image from "next/image"
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "./ui/card"
import { TRentals } from "@/types/bike.types"
import { Bike, CircleQuestionMark } from "lucide-react"
import { Tooltip, TooltipContent, TooltipTrigger } from "./ui/tooltip"
import { Button } from "./ui/button"
import { Badge } from "./ui/badge"
import { useBikeId, useToken } from "@/store/common.store"
import Link from "next/link"

const RentalCards = (data: TRentals) => {
  const { token } = useToken()
  const { setBikeSelected } = useBikeId()
  return (
    <>
      <Card className="shadow-sm hover:shadow-md hover:scale-105 transition-all">
        <CardContent>
          <Image
            alt="vehicle"
            src={"/Scooter.png"}
            height={200}
            width={250}
            className="rounded-xl"
          />
        </CardContent>
        <CardHeader>
          <Badge variant={"secondary"}>{data.category}</Badge>
          <CardTitle>{data.name}</CardTitle>
          <CardDescription className="line-clamp-3 min-h-[3.6em]">
            {data.description ?? "No description"}
          </CardDescription>
          <div>
            <div className="flex items-center justify-start gap-2">
              <div className="flex items-center justify-between gap-2">
                <p className="font-bold text-2xl">₹{data.hourlyRate}</p>
                <Tooltip>
                  <TooltipTrigger asChild>
                    <CircleQuestionMark size={20} className="text-blue-500" />
                  </TooltipTrigger>
                  <TooltipContent>
                    <p>Hourly Rate</p>
                  </TooltipContent>
                </Tooltip>
              </div>
              <div className="ml-auto">
                <Link href={token === null ? "/auth" : "/booking"}>
                  <Button
                    className="bg-orange-500 hover:bg-orange-600 hover:cursor-pointer"
                    onClick={() => setBikeSelected(data.id)}
                  >
                    Book
                    <Bike />
                  </Button>
                </Link>
              </div>
            </div>
          </div>
        </CardHeader>
      </Card>
    </>
  )
}
export default RentalCards
