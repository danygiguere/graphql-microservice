
import { Post, PostWithImages, fetchPost } from "../../data/posts";

export default async function Post({ params }: { params: { id: number } }) {
  const response = await fetchPost(params.id);
  console.log("response: ", response);
  console.log("response.data: ", response.data);
  const post = response.data.postWithImages;

  return (
    <>
      <section className="h-[calc(100vh-40px)] bg-slate-950">
        <div className="md:container md:mx-auto px-4 py-4">
          <div className="text-xs text-slate-300">
            <h1 className="text-lg">{post.title}</h1>
            <p className="pt-4">{post.description}</p>
          </div>
        </div>
      </section>
    </>
  );
}
