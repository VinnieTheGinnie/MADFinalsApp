package com.mobileappdevelopersclub.fapp.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.MainActivity;
import com.mobileappdevelopersclub.fapp.R;


public class GpaCalculatorFragment extends FappFragment {
	
	private View mView;
	
	@InjectView(R.id.editText1) EditText ed1;
	@InjectView(R.id.editText2) EditText ed2;
	@InjectView(R.id.editText3) EditText ed3;
	@InjectView(R.id.editText4) EditText ed4;
	@InjectView(R.id.editText5) EditText ed5;
	@InjectView(R.id.editText6) EditText ed6;
	@InjectView(R.id.editText7) EditText ed7;
	@InjectView(R.id.editText8) EditText ed8;
	@InjectView(R.id.editText9) EditText ed9;
	@InjectView(R.id.editText10) EditText ed10;
	@InjectView(R.id.editText11) EditText ed11;  
	@InjectView(R.id.editText12) EditText ed12;
	@InjectView(R.id.editText13) EditText ed13;
	@InjectView(R.id.editText14) EditText ed14;
	@InjectView(R.id.btnCalc) Button bt1;
	@InjectView(R.id.textView1) TextView tv1;
	@InjectView(R.id.textView2) TextView tv2;
	
	public static GpaCalculatorFragment newInstance() {
		GpaCalculatorFragment fragment = new GpaCalculatorFragment();
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	 @OnClick(R.id.btnCalc) void onCalculateClicked() {
		 	Runnable textViewUpdater = new Runnable(){
		 		public void run(){
		 			tv2.setText(performGpaCalculation());
		 		}
		 	};
		 	textViewUpdater.run();
	 }
	 
	 public String performGpaCalculation(){
		 double totalCreditPoints = 0.00;
		 int totalCredits = 0;
		 
		 List<Double> creditList = new ArrayList<Double>();
		 List<Double> gradeList = new ArrayList<Double>();
		 
		 //Sets all un entered text fields to 0
		 if(ed1.getText().toString().equals("")){
			 creditList.add(0.00);
		 }else{
			 creditList.add(Double.valueOf(ed1.getText().toString()));
		 } if(ed2.getText().toString().equals("")){
			 creditList.add(0.00);
		 } else{
			 creditList.add(Double.valueOf(ed2.getText().toString()));
		 } if(ed3.getText().toString().equals("")){
			 creditList.add(0.00);
		 } else{
			 creditList.add(Double.valueOf(ed3.getText().toString()));
		 } if(ed4.getText().toString().equals("")){
			 creditList.add(0.00);
		 } else{
			 creditList.add(Double.valueOf(ed4.getText().toString()));
		 } if(ed5.getText().toString().equals("")){
			 creditList.add(0.00);
		 } else{
			 creditList.add(Double.valueOf(ed5.getText().toString()));
		 } if(ed6.getText().toString().equals("")){
			 creditList.add(0.00);
		 } else{
			 creditList.add(Double.valueOf(ed6.getText().toString()));
		 } if(ed7.getText().toString().equals("")){
			 creditList.add(0.00);
		 } else{
			 creditList.add(Double.valueOf(ed7.getText().toString()));
		 }
		 
		 gradeList.add(convertToCreditPoints(ed8.getText().toString()));
		 gradeList.add(convertToCreditPoints(ed9.getText().toString()));
		 gradeList.add(convertToCreditPoints(ed10.getText().toString()));
		 gradeList.add(convertToCreditPoints(ed11.getText().toString()));
		 gradeList.add(convertToCreditPoints(ed12.getText().toString()));
		 gradeList.add(convertToCreditPoints(ed13.getText().toString()));
		 gradeList.add(convertToCreditPoints(ed14.getText().toString()));
		 
		 for(int i=0;i<=6;i++){
			 totalCredits += creditList.get(i);
			 totalCreditPoints += (creditList.get(i) * gradeList.get(i));
		 }
		 return String.valueOf(totalCreditPoints / totalCredits).substring(0,4);
	 }
	 
	 public double convertToCreditPoints(String string){
		 if(string.equals(Constants.A_PLUS)){
			 return Constants.A_PLUS_INT;
		 } if(string.equals(Constants.A)){
			 return Constants.A_INT;
		 } if(string.equals(Constants.A_MINUS)){
			 return Constants.A_MINUS_INT;
		 } if(string.equals(Constants.B_PLUS)){
			 return Constants.B_PLUS_INT;
		 } if(string.equals(Constants.B)){
			 return Constants.B_INT;
		 } if(string.equals(Constants.B_MINUS)){
			 return Constants.B_MINUS_INT;
		 } if(string.equals(Constants.C_PLUS)){
			 return Constants.C_PLUS_INT;
		 } if(string.equals(Constants.C)){
			 return Constants.C_INT;
		 } if(string.equals(Constants.C_MINUS)){
			 return Constants.C_MINUS_INT;
		 } if(string.equals(Constants.D_PLUS)){
			 return Constants.D_PLUS_INT;
		 } if(string.equals(Constants.D)){
			 return Constants.D_INT;
		 } if(string.equals(Constants.D_MINUS)){
			 return Constants.D_MINUS_INT;
		 } if(string.equals(Constants.F)){
			 return Constants.F_INT;
		 } if(string.equals("")){
			 return 0;
		 } else{
			 //If one of the above strings arent entered an error toast pops up
			 Toast.makeText(getActivity(),"To see correct GPA, "
		 		+ "please enter valid grades.", Toast.LENGTH_SHORT).show();
			 return 0;
		 }
	 }
	 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		mView = inflater.inflate(R.layout.gpa_calculator , null);
		
		Views.inject(this, mView);

		return mView;
	}

}
