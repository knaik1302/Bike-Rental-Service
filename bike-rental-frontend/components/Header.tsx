import { BikeIcon, CircleUserRound, Phone } from "lucide-react"
import Link from "next/link"

const Header = ({ children }: { children: React.ReactNode }) => {
  return (
    <>
      <div className="sticky top-0 z-10 shadow-md">
        <span className="p-1 text-xs flex justify-end items-center pr-4 gap-2 bg-green-50">
          <Phone size={15} fill="#000" />
          +91 9090909090
        </span>
        <div className="flex items-center justify-center px-2 py-4 bg-slate-900">
          <h1 className="flex-1 flex justify-start gap-2 text-3xl font-extrabold items-center text-white">
            <BikeIcon fill="#ED7D3A" className="text-orange-400 -rotate-45" />
            Moto Rentals
          </h1>
          <nav className="flex-3 pl-8">
            <ul className="flex gap-4 text-xl">
              <li className="text-green-400 underline cursor-pointer hover:text-green-600">
                Book Bikes
              </li>
              <li className="text-green-400 underline cursor-pointer hover:text-green-600">
                About us
              </li>
            </ul>
          </nav>
          <div className="flex-1 flex justify-end pr-4">
            <Link href={"/auth"}>
              <CircleUserRound size={30} className="text-white" />
            </Link>
          </div>
        </div>
      </div>
      <div>{children}</div>
    </>
  )
}
export default Header
