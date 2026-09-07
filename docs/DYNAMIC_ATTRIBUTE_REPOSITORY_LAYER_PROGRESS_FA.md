# Dynamic Attribute Repository Layer

## Purpose

Connect Dynamic Attribute Engine to persistence without coupling business logic to Room.

## Flow

UI -> ViewModel -> UseCase -> Repository -> DAO -> Database

## Rule

Core does not know business types such as mobile, boutique or beauty. Attributes are resolved from Business Profile and Schema.
