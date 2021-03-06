/*
    ��������� ��������� ������� ����������� ��������� �� ������� �����.
    �������� � ����������� ��� ���������� ������, ������� ���� - ������.
    ������� ����������� ������ ����������� ���.
    ������������� ����� ������� �� ����������� �����,
    ������ � ��������� ��������������� ������.
*/

#include <vcl.h>
#pragma hdrstop

#include "kld_.h"



#pragma package(smart_init)
#pragma resource "*.dfm"


#include <DateUtils.hpp>

TForm1 *Form1;

#define BACKGROUND
#undef BACKIMAGE

AnsiString stMonth[12] = {"������","�������","����","������",
                          "���","����","����","������",
                          "��������","�������","������","�������"};

AnsiString stDay[7] = {"��","��","��", "��","��","��","��"};

// ����������� ��� � ������� dd.mm
AnsiString holiday ="01.01;02.01;07.01;23.02;08.03;01.05;09.05;07.11;12.12;";

Word aYear, aMonth, aDay; // ���, �����, ����

__fastcall TForm1::TForm1(TComponent* Owner)
    : TForm(Owner)
{
   DecodeDate(Now(),aYear,aMonth,aDay);
   UpDown1->Position = aMonth;
   #ifdef BACKIMAGE
   // �������� ������� ��������
   backimage = new Graphics::TBitmap();
   try {
        backimage->LoadFromFile("back.bmp");
   }
   catch (EFOpenError &e) {
        return;
   }
   #endif
}

// ���������� ��������� ������������� ����������� �����
// � ������� �����: 01, 02 � �.�.
AnsiString __fastcall IntToStr_(int i)
{
  AnsiString s;
  if ( IntToStr(i).Length() == 1) s = "0"+IntToStr(i);
  else s = IntToStr(i);
  return s;
}

// ��������� ������� Paint - ������� ���������
void __fastcall TForm1::FormPaint(TObject *Sender)
{
    Word aDayOfWeek; // ���� ������
    AnsiString st;   // ���� � ������� dd.mm

    int x, y,
        dx, dy, // ��� �������� � ����� ����
        x0,y0;  // ����� ������� ���� ������� ������ ���������

    int i;

    x0 = 50; y0 = 40;
    dx = 20; dy = 20;

    Caption = "��������� " + IntToStr(aYear);
    #ifdef BACKGROUND
    Canvas->Brush->Color = clBackground;
    #endif

    #ifdef BACKIMAGE
    Canvas->Draw(0,0,backimage);
    #else
    Canvas->Pen->Color = Canvas->Brush->Color;
    Canvas->Rectangle(0,0,ClientWidth,ClientHeight);
    #endif
    // ������� ��������� �� ������� �����
    Canvas->Brush->Style = bsClear;
    Canvas->Font->Size = 12;
    Canvas->Font->Color = clBlack;
    Canvas->TextOutA(x0,y0-35, stMonth[aMonth-1]);

    Canvas->Font->Size = 10;

    // ������ ������� - �������� ���� ������
    x = x0 - 30;
    y = y0;
    for ( i = 0; i < 7; i++)
    {
        if ( i < 5 )
            Canvas->Font->Color = clBlack;
        else
            Canvas->Font->Color = clRed;

        Canvas->TextOutA(x,y, stDay[i]);
        y += dy;
    }


    /* ��������� ���� ������, � ��������
      ���������� ����� */
    aDayOfWeek = DayOfTheWeek( EncodeDate(aYear,aMonth,1));

    x = x0;
    y = y0 + dy * (aDayOfWeek-1);

    for ( i = 1; i <= DaysInAMonth(aYear,aMonth); i++ )
    {
        // ��������, �� �������� �� ���� �����������
        st = IntToStr_(i) + "." + IntToStr_(aMonth);
        if ( holiday.Pos(st) !=0 )
            Canvas->Font->Color = clRed; // �����������
        else  // �������
            if ( aDayOfWeek < 6 )
                Canvas->Font->Color = clBlack;
            else
                Canvas->Font->Color = clRed; // ������� � �����������

        Canvas->TextOutA(x,y, IntToStr(i));

        if ( i == aDay) {
            // �������� ����������� �����
            // �����
            Canvas->Brush->Style = bsClear;
            Canvas->Pen->Color = clGray;
            Canvas->Rectangle(x-3,y-1,x+dx-2,y+dy-1);
         }

        if ( aDayOfWeek != 7 ) {
            y += dy;
            aDayOfWeek ++;
        }
        else {
            aDayOfWeek =1;
            x += dx;
            y = y0;
        }
    }
}

// ������ �� ������ ���������� UpDown
void __fastcall TForm1::UpDown1Click(TObject *Sender, TUDBtnType Button)
{
     switch (UpDown1->Position) {
        case 0 : UpDown1->Position = 12; aYear--; break;
        case 13: UpDown1->Position = 1;  aYear++;; break;
     };

     aMonth = UpDown1->Position;
     Paint(); // �������� ���������
}

// ������� �������
void __fastcall TForm1::FormKeyDown(TObject *Sender, WORD &Key,
      TShiftState Shift)
{
   if ( Key == 27) // ������� <Esc>
        Form1->Close(); // ��������� ������ ���������
}


/*
 C������ ���������� ��������� WM_NCHITTEST ���� ����������,
 ������ �������/���������� ������ ����.
 ����������� ������� ��������� ��������� ��. � h-�����
 */

void __fastcall TForm1::WMNCHITTEST(TMessage &Message)
{
         /* ����� ������� "������", ��� ������
            ���� ������ � ��������� ���� */
         Message.Result = HTCAPTION;
}

