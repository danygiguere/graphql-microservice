
import { PostComponent } from "../components/PostComponent";
import { Post, PostWithImages, fetchPosts } from "../data/posts";

export default async function Posts() {
  const response =  await fetchPosts()
  console.log("response: ", response);
  console.log("response.data: ", response.data);

  return (
    <>
      <section className="h-[calc(100vh-40px)] bg-slate-950">
        <div className="md:container md:mx-auto px-4 py-4">
          <div className="text-xs text-slate-300">
            <ul className="grid grid-cols-2 md:grid-cols-2 lg:grid-cols-4 xl:grid-cols-6 gap-4">
              {response.data.postsWithImages.map((post: PostWithImages) => (
                <PostComponent key={post.id} post={post} />
              ))}
            </ul>
          </div>
        </div>
      </section>
    </>
  );
}
