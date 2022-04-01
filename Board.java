package models;

import java.sql.*;
import java.util.*;

public class Board{
	private Integer boardId;
	private String board;

	public Board(Integer boardId){
		this.boardId = boardId;	
	}

	public void setBoardId(Integer boardId){
		this.boardId = boardId;
	}

	public void setBoard(String board){
		this.board = board;
	}

	public Integer getBoardId(){
		return boardId;
	}

	public String getBoard(){
		return board;
	}
}