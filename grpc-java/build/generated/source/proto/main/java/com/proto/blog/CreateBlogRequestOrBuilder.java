// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: blog/blog.proto

package com.proto.blog;

public interface CreateBlogRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:blog.CreateBlogRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * id should not matter
   * </pre>
   *
   * <code>.blog.Blog blog = 1;</code>
   * @return Whether the blog field is set.
   */
  boolean hasBlog();
  /**
   * <pre>
   * id should not matter
   * </pre>
   *
   * <code>.blog.Blog blog = 1;</code>
   * @return The blog.
   */
  com.proto.blog.Blog getBlog();
  /**
   * <pre>
   * id should not matter
   * </pre>
   *
   * <code>.blog.Blog blog = 1;</code>
   */
  com.proto.blog.BlogOrBuilder getBlogOrBuilder();
}