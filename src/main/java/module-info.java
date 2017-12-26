module pe.edu.ltmj.main {
	exports pe.edu.ltmj.main;
	exports pe.edu.ltmj.controller;

	requires java.sql;
	requires java.annotation;
	requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.aop;
    requires spring.web;
    requires spring.expression;

    requires spring.boot;
    requires spring.boot.autoconfigure;

    opens pe.edu.ltmj.main;
}