// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: blog/blog.proto

package com.proto.blog;

public interface CreateBlogResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:blog.CreateBlogResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * id would be the one corresponding to the mongodb
   * </pre>
   *
   * <code>.blog.Blog blog = 1;</code>
   * @return Whether the blog field is set.
   */
  boolean hasBlog();
  /**
   * <pre>
   * id would be the one corresponding to the mongodb
   * </pre>
   *
   * <code>.blog.Blog blog = 1;</code>
   * @return The blog.
   */
  com.proto.blog.Blog getBlog();
  /**
   * <pre>
   * id would be the one corresponding to the mongodb
   * </pre>
   *
   * <code>.blog.Blog blog = 1;</code>
   */
  com.proto.blog.BlogOrBuilder getBlogOrBuilder();
}