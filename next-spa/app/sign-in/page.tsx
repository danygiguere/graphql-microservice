import Header from "../components/Header";

export default function Login() {
  return (
    <>
      
      <section className="h-[calc(100vh-40px)] bg-slate-950">
        <div className="md:container md:mx-auto pt-28 pb-12 px-4">
          <div className="grid justify-center">
            <div className="sm:w-[500px]">
              <form method="post" className="w-full sm:w-[500px]">
                <div>
                  <label htmlFor="" className="text-white">
                    Your email
                  </label>
                  <input
                    id="email"
                    type="email"
                    name="email"
                    className="p-3 w-full border border-slate-200 rounded-lg bg-slate-800 text-white"
                  ></input>
                </div>
                <div className="mt-4">
                  <label htmlFor="" className="text-white">
                    Your password
                  </label>
                  <input
                    id="password"
                    type="password"
                    name="password"
                    className="p-3 w-full border border-slate-200 rounded-lg bg-slate-800 text-white"
                  ></input>
                </div>
                <button className="text-center border rounded-full px-4 py-2 mt-4 text-sm font-medium text-white bg-[#1e293b]">
                  Sign in
                </button>
              </form>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}
