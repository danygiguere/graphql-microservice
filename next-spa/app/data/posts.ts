import { Image } from "./images";

export type Post = {
  id: number;
  title: string;
};  

export type PostWithImages = {
  id: number;
  userId: number;
  title: string;
  description: String;
  images: Image[];
};  

export async function fetchPosts() {
  const res = await fetch("http://localhost:8011/graphql", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      query: `
        query PostsWithImages {
            postsWithImages {
                id
                userId
                title
                description
                images {
                    id
                    postId
                    url
                }
            }
        }
      `,
    }),
  });
  if (!res.ok) return undefined;
  return await res.json();
}

export async function fetchPost(id: number) {
  const res = await fetch("http://localhost:8011/graphql", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      query: `
        query PostWithImages {
            postWithImages(idFilter: ${id}) {
                id
                userId
                title
                description
            }
        }
      `,
    }),
  });
  if (!res.ok) return undefined;
  const response = await res.json();

  return await response;
}