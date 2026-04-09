import Link from "next/link"

export default function HomePage() {
  return (
    <main className="min-h-screen bg-gray-50">
      {/* Hero Section */}
      <section className="relative bg-linear-to-r from-green-600 to-green-500 text-white">
        <div className="max-w-7xl mx-auto px-6 py-24 flex flex-col gap-6">
          <h1 className="text-4xl md:text-6xl font-extrabold leading-tight">
            Rent Bikes. <br /> Ride Freely.
          </h1>

          <p className="max-w-xl text-lg text-green-100">
            Affordable, reliable bike rentals for city rides, adventures, and
            long journeys.
          </p>

          <div className="flex gap-4 mt-4">
            <Link href={"/rentals"}>
              <button className="bg-white text-green-600 font-semibold px-6 py-3 rounded-lg hover:bg-green-100 transition">
                Explore Bikes
              </button>
            </Link>
            {/* <button className="border border-white px-6 py-3 rounded-lg hover:bg-white hover:text-green-600 transition">
              How It Works
            </button> */}
          </div>
        </div>
      </section>

      {/* Search Section */}
      {/* <section className="relative -mt-10 z-10">
        <div className="max-w-5xl mx-auto bg-white shadow-xl rounded-xl p-6 flex flex-col md:flex-row gap-4">
          <input
            type="text"
            placeholder="Pickup location"
            className="flex-1 border rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-green-500"
          />
          <input
            type="date"
            className="border rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-green-500"
          />
          <input
            type="date"
            className="border rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-green-500"
          />
          <button className="bg-green-600 text-white px-6 py-3 rounded-lg hover:bg-green-700 transition">
            Search
          </button>
        </div>
      </section> */}

      {/* Featured Bikes */}
      {/* <section className="max-w-7xl mx-auto px-6 py-20">
        <h2 className="text-3xl font-bold mb-10 text-center">Featured Bikes</h2>

        <div className="grid gap-8 sm:grid-cols-2 lg:grid-cols-3">
          {["Mountain Bike", "City Scooter", "Royal Cruiser"].map((bike, i) => (
            <div
              key={i}
              className="bg-white rounded-xl shadow-md hover:shadow-xl transition overflow-hidden"
            >
              <div className="h-40 bg-gray-200 flex items-center justify-center text-gray-500">
                Bike Image
              </div>

              <div className="p-5 flex flex-col gap-3">
                <h3 className="text-xl font-semibold">{bike}</h3>
                <p className="text-gray-600 text-sm">
                  Comfortable, reliable, and perfect for daily rides.
                </p>

                <div className="flex justify-between items-center mt-3">
                  <span className="text-green-600 font-bold">₹499 / day</span>
                  <button className="text-sm font-medium text-green-600 hover:underline">
                    Rent Now
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </section> */}

      {/* Why Choose Us */}
      <section className="bg-white py-20">
        <div className="max-w-7xl mx-auto px-6">
          <h2 className="text-3xl font-bold text-center mb-12">
            Why Choose Us
          </h2>

          <div className="grid gap-8 md:grid-cols-3 text-center">
            <div className="p-6">
              <div className="text-green-600 text-4xl mb-4">🏍️</div>
              <h3 className="font-semibold text-xl mb-2">
                Well Maintained Bikes
              </h3>
              <p className="text-gray-600">
                Every bike is regularly serviced and safety checked.
              </p>
            </div>

            <div className="p-6">
              <div className="text-green-600 text-4xl mb-4">💰</div>
              <h3 className="font-semibold text-xl mb-2">Affordable Pricing</h3>
              <p className="text-gray-600">
                No hidden charges. Pay only for what you use.
              </p>
            </div>

            <div className="p-6">
              <div className="text-green-600 text-4xl mb-4">⚡</div>
              <h3 className="font-semibold text-xl mb-2">Quick Booking</h3>
              <p className="text-gray-600">
                Book your bike in under 2 minutes.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* CTA */}
      <section className="bg-green-600 text-white py-16">
        <div className="max-w-6xl mx-auto px-6 text-center">
          <h2 className="text-3xl font-bold mb-4">Ready to Ride?</h2>
          <p className="mb-6 text-green-100">
            Find your perfect bike and hit the road today.
          </p>
          <Link href={"/rentals"}>
            <button className="bg-white text-green-600 px-8 py-3 rounded-lg font-semibold hover:bg-green-100 transition">
              Get Started
            </button>
          </Link>
        </div>
      </section>
    </main>
  )
}
