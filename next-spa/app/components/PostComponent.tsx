import Link from "next/link";
import { Post, PostWithImages } from "../data/posts";
import { FunctionComponent } from "react";

export const PostComponent: FunctionComponent<{ post: PostWithImages }> = ({ post }) => {
  return (
    <li className="border border-slate-200 rounded-lg">
      <Link href={`/posts/${post.id}`}>
        <div className="aspect-square relative overflow-hidden z-[100] border-b rounded-t-lg">
          <img
            className="absolute h-full w-auto max-w-max -translate-x-1/2 left-1/2 z-[50] rounded-t-lg"
            src={post.images[0]?.url}
            alt=""
          ></img>
        </div>
        <div className="px-4 py-2 rounded-b-lg">
          <p className="text-white">{post.title}</p>
        </div>
      </Link>
    </li>
  );
};
