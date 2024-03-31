"use client";

import Link from "next/link";

export default function Header() {
  return (
    <header>
      <nav className="fixed w-full z-[150] bg-[#1e293b]">
        <div className="md:container md:mx-auto text-white relative text-sm">
          <div
            id="menu-button"
            className="md:hidden cursor-pointer absolute top-5 right-4"
          >
            <div className="w-8 h-8 group relative">
              <span
                id="bar-1"
                className="absolute w-6 h-[3px] mb-1 block bg-white group-hover:bg-[#07f] rounded-sm top-1 right-0"
              ></span>
              <span
                id="bar-2"
                className="absolute w-6 h-[3px] mb-1 block bg-white group-hover:bg-[#07f] rounded-sm top-2.5 right-0"
              ></span>
              <span
                id="bar-3"
                className="absolute w-6 h-[3px] mb-1 block bg-white group-hover:bg-[#07f] rounded-sm top-2.5 right-0"
              ></span>
              <span
                id="bar-4"
                className="absolute w-6 h-[3px] mb-1 block bg-white group-hover:bg-[#07f] rounded-sm top-4 right-0"
              ></span>
            </div>
          </div>
          <div className="md:grid md:grid-cols-[150px_1fr] md:pr-4">
            <div className="md:justify-self-start">
              <Link href={`/posts`} className="px-4 pt-2 pb-2 grid w-[150px]">
                <img
                  className="w-[50px]"
                  src="https://placehold.co/400x400"
                  alt=""
                ></img>
              </Link>
            </div>
            <div
              id="menu"
              className="hidden md:grid md:grid-flow-col md:auto-cols-auto"
            >
              <ul className="md:grid md:grid-flow-col md:auto-cols-auto md:justify-self-end">
                <>
                  <li>
                    <Link
                      href={`/sign-in`}
                      className="h-full px-4 py-2 grid md:px-2 md:py-5 hover:bg-white hover:text-black"
                    >
                      Sign In
                    </Link>
                  </li>
                  <li className="">
                    <Link
                      href={`/sign-up`}
                      className="h-full px-4 py-2 grid md:px-2 md:py-5 hover:bg-white hover:text-black"
                    >
                      Sign Up
                    </Link>
                  </li>
                </>
              </ul>
            </div>
          </div>
        </div>
      </nav>
      <div className="border-b border-slate-200">
        <div className="md:container md:mx-auto pt-[63.63px] px-4">
          <ul className="grid grid-flow-col auto-cols-min overflow-x-auto">
            <li className="grid">
              <a
                className="whitespace-nowrap py-2 px-4 text-xs text-slate-700 hover:bg-slate-800 hover:text-slate-100"
                href=""
              >
                Item 1
              </a>
            </li>
            <li className="grid">
              <a
                className="whitespace-nowrap py-2 px-4 text-xs text-slate-700 hover:bg-slate-800 hover:text-slate-100"
                href=""
              >
                Item 2
              </a>
            </li>
          </ul>
        </div>
      </div>
    </header>
  );
}